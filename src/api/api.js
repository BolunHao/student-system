// api.js

// 模拟后端 API
export const fakeAPI = {
    getCourses: () => {
      return new Promise((resolve) => {
        setTimeout(() => {
          // 假设从后端获取的课程数据
          const courses = [
            { code: 'CSC2011', name: 'Engineering Mathematics Systems Modelling', description: 'This course covers mathematical modeling techniques in engineering.' },
            { code: 'CSC2012', name: 'Software Engineering', description: 'This course covers software development principles and practices.' },
            { code: 'CSC2013', name: 'Data Structures and Algorithms', description: 'This course covers data structures and algorithms.' },
            // 其他课程...
          ];
          resolve(courses);
        }, 500); // 模拟0.5秒延迟
      });
    },

    // const assignments = {
    //   assignments: {
    //     courseName: 'Course ABC',
    //     courseID: '12345',
    //     instructor: 'Professor XYZ',
    //     classTime: 'Mon/Wed/Fri 9:00-10:30',
    //     examType: 'Final Exam',
    //     examTime: 'May 20th, 2024'
    //   }
    // }
    getGrades: (code) => {
      {
        return new Promise((resolve) => {
          setTimeout(() => {
            // 假设从后端获取的课程成绩数据
            const courseScore = {
              CSC2011: {
                'Math': 90,
                'Physics': 85,
                'Chemistry': 88,
                'Biology': 92,
                'English': 87
              },
              CSC2012: {
                'Math': 90,
                'Physics': 85,
                'Chemistry': 88,
                'Biology': 92,
                'English': 87
              },
              CSC2013: {
                'Math': 90,
                'Physics': 85,
                'Chemistry': 88,
                'Biology': 92,
                'English': 87
              },
            };
            resolve(courseScore[code]);
          }, 500); // 模拟0.5秒延迟
        });

      }
    },
    getAssignments: (code) => {
      return new Promise((resolve) => {
        setTimeout(() => {
          // 假设从后端获取的assignments
          const assignments = {
            CSC2011: {
              courseName: 'Engineering Mathematics Systems Modelling',
              courseID: 'CSC2011',
              instructor: 'Dr. John Doe',
              classTime: 'Mon/Wed/Fri 9:00-10:30',
              examType: 'Final Exam',
              examTime: 'May 20th, 2024'
            },
            CSC2012: {
              courseName: 'Software Engineering',
              courseID: 'CSC2012',
              instructor: 'Dr. Jane Smith',
              classTime: 'Tue/Thu 13:00-14:30',
              examType: 'Final Exam',
              examTime: 'May 21st, 2024'
            },
            CSC2013: {
              courseName: 'Data Structures and Algorithms',
              courseID: 'CSC2013',
              instructor: 'Dr. Alice Johnson',
              classTime: 'Mon/Wed/Fri 13:00-14:30',
              examType: 'Final Exam',
              examTime: 'May 22nd, 2024'
            },
            // 其他课程的assignments...
          };
          resolve(assignments[code]);
        }, 500); // 模拟0.5秒延迟
      });
    },

    getCourseContent: (code) => {
      return new Promise((resolve) => {
        setTimeout(() => {
          // 假设从后端获取的课程内容数据
          const courseContent = {
            CSC2011: {
              syllabus: '- Functions, algebra and graphs of functions',
              assignments: {
                requirements: 'Complete the following assignments:',
                tasks: [
                  { name: 'Assignment 1', description: 'Description for Assignment 1' },
                  { name: 'Assignment 2', description: 'Description for Assignment 2' },
                  { name: 'Assignment 3', description: 'Description for Assignment 3' }
                ],
                submission: 'Submit assignments by the due date.'
              },
              grade: 'Course grade content goes here.',
              meetingAttendance: 'Course meeting attendance content goes here.',
              reflect: 'Course reflection content goes here.'
            },
            CSC2012: {
              syllabus: '- Software development principles',
              assignments: {
                requirements: 'Complete the following assignments:',
                tasks: [
                  { name: 'Assignment 1', description: 'Description for Assignment 1' },
                  { name: 'Assignment 2', description: 'Description for Assignment 2' },
                  { name: 'Assignment 3', description: 'Description for Assignment 3' }
                ],
                submission: 'Submit assignments by the due date.'
              },
              grade: 'Course grade content goes here.',
              meetingAttendance: 'Course meeting attendance content goes here.',
              reflect: 'Course reflection content goes here.'
            },
            CSC2013: {
              syllabus: '- Data structures and algorithms',
              assignments: {
                requirements: 'Complete the following assignments:',
                tasks: [
                  { name: 'Assignment 1', description: 'Description for Assignment 1' },
                  { name: 'Assignment 2', description: 'Description for Assignment 2' },
                  { name: 'Assignment 3', description: 'Description for Assignment 3' }
                ],
                submission: 'Submit assignments by the due date.'
              },
              grade: 'Course grade content goes here.',
              meetingAttendance: 'Course meeting attendance content goes here.',
              reflect: 'Course reflection content goes here.'
            },
            // 其他课程的内容...
          };
  
          resolve(courseContent[code]);
        }, 500); // 模拟0.5秒延迟
      });
    }
};
