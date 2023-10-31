import axios from "axios";
import cookie from "react-cookies";
import { createHeaders } from "./header";

const COURSE_API_ADMIN_URL = 'http://localhost:8080/api/course-data';

class CourseDataService {
    getAllCourse() { return axios.get(COURSE_API_ADMIN_URL, createHeaders()); }
    getById(id) { return axios.get(COURSE_API_ADMIN_URL + "/"+ id, createHeaders()); }

    addCourse(courseData) { return axios.post(COURSE_API_ADMIN_URL, courseData, createHeaders()); }

    updateCourse(id, courseData) { return axios.put(COURSE_API_ADMIN_URL + '/' + id, courseData, createHeaders()); }

    deleteCourse(id) { return axios.delete(COURSE_API_ADMIN_URL + '/' + id, createHeaders()); }
}

export default new CourseDataService()