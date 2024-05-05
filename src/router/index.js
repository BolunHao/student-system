import Vue from 'vue';
import VueRouter from 'vue-router';
import Courses from '../components/Courses.vue';
import Selection from '../components/Undergraduate.vue';
import Programs from '../components/Programs.vue';
import Timetable from '../components/Timetable.vue';
import AcademicGuidance from '../components/AcademicGuidance.vue';
import Exam from '../components/Exam.vue';
import Profile from '../components/Profile.vue';
import ChangeStatus from '../components/ChangeStatus.vue';
import ChangePassword from '../components/ChangePassword.vue';
import Postgraduate from '../components/Postgraduate.vue';
import CourseDetail from '../components/CourseDetail.vue'; 

Vue.use(VueRouter);

const routes = [
  { path: '/courses', component: Courses },
  { path: '/selection', component: Selection },
  { path: '/programs', component: Programs },
  { path: '/timetable', component: Timetable },
  { path: '/academic-guidance', component: AcademicGuidance },
  { path: '/exam', component: Exam },
    { path: '/profile', component: Profile },
    { path: '/change-status', component: ChangeStatus },
    { path: '/change-password', component: ChangePassword },
    { path: '/course/:code', component: CourseDetail , props: true},
    { path: '/postgraduate', component: Postgraduate },
];

const router = new VueRouter({
  routes
});

export default router;
