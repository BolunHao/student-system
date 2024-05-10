package com.management.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.management.ftp.UploadUtil;
import com.management.Parametric.User;
import com.management.service.services.UserService;
import com.management.utils.EnumCode;
import com.management.utils.ExcelUtil;
import com.management.utils.ResultUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;


@RestController
@RequestMapping(value = "user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    Map<String, String> uploadImg;

    /**
     * Upload user avatar
     */
    @ResponseBody
    @RequestMapping(value = "/uploadHander", method = RequestMethod.POST)
    public String uploadLogo(HttpServletRequest request) {
        uploadImg = new HashMap<String, String>();
        uploadImg = UploadUtil.uploadImage(request, "vue_shiro_photo/userImg");
        return uploadImg.get("pic");
    }

    @RequestMapping("/upload")
    @ResponseBody
    public String handleFileUpload(MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                BufferedOutputStream out = new BufferedOutputStream(
                        new FileOutputStream(new File(
                                file.getOriginalFilename())));
                System.out.println(file.getName());
                out.write(file.getBytes());
                out.flush();
                out.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return "Upload Failed," + e.getMessage();
            } catch (IOException e) {
                e.printStackTrace();
                return "Upload Failed," + e.getMessage();
            }

            return "Uploaded successfully";

        } else {
            return "Upload failed because the file was empty.";
        }
    }

    /**
     * @desc: user search
     */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public Object findUserByPage(Integer startPage,Integer pageSize,User user) {
        Page<User> page = new Page<User>(startPage,pageSize);
        List<User> list = userService.findUserByPage(page,user);
        return ResultUtil.result(EnumCode.OK.getValue(), "Request successful", list, page.getTotal());
    }

    /**
     * @desc: new subscriber
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Object addUser(@Valid User userVo, BindingResult bindingResult) {
        return userService.addUser(userVo);
    }

    /**
     * @desc: Batch Delete Users
     */
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public Object delUsers(User user) {
        String[] ids = user.getIds();
        if (null == ids || ids.length == 0) {
            return ResultUtil.result(EnumCode.BAD_REQUEST.getValue(), EnumCode.BAD_REQUEST.getText());
        }
        return userService.delUsers(ids);
    }

    /**
     * Modify user status
     */
    @RequestMapping(value = "/status", method = RequestMethod.POST)
    public Object editUserStatus(User dto) {
        if (StringUtils.isEmpty(dto.getId()) || null == dto.getEnable()) {
            return ResultUtil.result(EnumCode.BAD_REQUEST.getValue(), EnumCode.BAD_REQUEST.getText());
        }
        return userService.editUserStatus(dto);
    }

    /**
     * Modification of User's Personal Information by the User
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Object editUserInfo(User vo) {
        return userService.editUserInfo(vo);
    }


    /**
     * Exporting Reports
     *
     * @return
     */
    @RequestMapping(value = "/export")
    @ResponseBody
    public void export(@RequestBody(required = false) User user,String username,HttpServletResponse response) throws Exception {
        if (user ==null && !StringUtils.isEmpty(username)){
            //GET 请求的参数
            user = new User();
            user.setUsername(username);
        }
        //get data
        List<User> list = userService.findAllUser(user);

        //excel title
        String[] title = {"name", "emial", "create_time", "Last login time","Role","availability"};

        //excel name
        String fileName = System.currentTimeMillis() + ".xls";

        //sheet name
        String sheetName = "user information";

        //If you don't have any data, just pass in null, the Excel tool class has a null judgement.
        String [][] content = null;

        if (list != null && list.size() > 0){
            content = new String[list.size()][title.length];
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            for (int i = 0; i < list.size(); i++) {
                content[i] = new String[title.length];
                User obj = list.get(i);
                content[i][0] = obj.getUsername();
                content[i][1] = obj.getEmail();
                content[i][2] = obj.getCreateTime() == null ? "" : sdf.format(obj.getCreateTime());
                content[i][3] = obj.getLastLoginTime() == null ? "": sdf.format(obj.getLastLoginTime());
                content[i][4] = obj.getRoleName();
                content[i][5] = obj.getEnable()==1 ? "yes" : "no";
            }
        }

        HSSFWorkbook wb = ExcelUtil.getHSSFWorkbook(sheetName, title, content);


        try {
            response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
            OutputStream os = response.getOutputStream();
            wb.write(os);
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Batch import users
     *
     */
    @RequestMapping(value = "/import")
    @ResponseBody
    public Object ExcelImport(MultipartFile[] multipartFiles) throws Exception {
        if (multipartFiles == null || multipartFiles.length < 1){
            return ResultUtil.result(EnumCode.INTERNAL_SERVER_ERROR.getValue(),"Empty data, import failed");
        }
        for (MultipartFile file : multipartFiles){
            List<String[]> list = ExcelUtil.readExcel(file);
            if (list.isEmpty()){
                return ResultUtil.result(EnumCode.INTERNAL_SERVER_ERROR.getValue(),"Empty data, import failed");
            }

            for (int i=0;i<list.size();i++){
                String[] values = list.get(i);
                //Here only imported 3 columns of data: name, mailbox and whether available (0, 1), other columns can be imported on their own, now convert the format and then write to the database, for example:
                //Import the role, according to the role name to find the role id, if the role id does not exist, you can default to the student and so on.
                User user = new User();
                user.setUsername(values[0]);
                user.setEmail(values[1]);
                user.setEnable(values[2] == null ? 1 : Integer.valueOf(values[2]));
                user.setCreateTime(new Date());
                user.setId(UUID.randomUUID().toString().replace("-",""));
                user.setPassword("a123456");
                user.setRoleId("3");
                userService.addUser(user);
            }
        }
        //The front-end can use the status code to determine whether the file was uploaded successfully or not
        return ResultUtil.result(EnumCode.OK.getValue(),"File uploaded successfully");
    }

    /**
     * Change the status of a student.
     * @param userId The ID of the student
     * @param status The new status (1: enrolled, 0: withdrawn)
     * @return A ResponseEntity indicating the success or failure of the operation
     */
    @PostMapping("/changeStatus")
    public ResponseEntity<?> changeUserStatus(@RequestParam String userId, @RequestParam Integer status) {
        if (StringUtils.isEmpty(userId) || (status != 0 && status != 1)) {
            return ResponseEntity.badRequest().body("Invalid request parameters.");
        }

        try {
            User user = userService.findById(userId);
            if (user == null) {
                return ResponseEntity.badRequest().body("User not found.");
            }
            user.setEnable(status);  // Assuming the 'enable' field in the User class indicates the user's status
            userService.updateUser(user);
            return ResponseEntity.ok("User status updated successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error updating user status: " + e.getMessage());
        }
    }

}
