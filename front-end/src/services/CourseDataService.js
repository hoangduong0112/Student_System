import axios from "axios";
import config from "./config";

const COURSE_API_ADMIN_URL = 'http://localhost:8080/api/course-data';

class CourseService {
    getAllCourse() { return axios.get(COURSE_API_ADMIN_URL, config); }
    getById(id) { return axios.get(COURSE_API_ADMIN_URL + "/"+ id, config); }

    addCourse(courseData) { return axios.post(COURSE_API_ADMIN_URL, courseData, config); }

    updateCourse(id, courseData) { return axios.put(COURSE_API_ADMIN_URL + '/' + id, courseData, config); }

    deleteCourse(id) { return axios.delete(COURSE_API_ADMIN_URL + '/' + id, config); }
}

export default new CourseService()