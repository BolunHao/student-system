package com.management.mapper.Mapper;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.management.Parametric.Module;
import com.management.vo.ModuleVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
/**
 * @author Wenqi Wang
 * @version 1.0
 * @since 2024-04-22
 */

public interface ModuleMapper extends BaseMapper<Module>{
    List<Module> getListByPage(Page<Module> page,@Param("name") String name);

    List<Module> getNotSelectedCourse(Page<Module> page, @Param("studentId") String studentId);

    List<Module> getSelectedCourse(Page<Module> page, @Param("studentId") String studentId);

    List<Module> selectHomeworkAndExam(ModuleVO moduleVO);

    List<ModuleVO> selectScoreByStudentId(ModuleVO vo);
}
