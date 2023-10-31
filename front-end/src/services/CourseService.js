import axios from "axios";
import cookie from "react-cookies";
import { createHeaders } from "./header";

const COURSE_API_ENDPOINT = 'http://localhost:8080/api/course';
class CourseService {
    getAll() { return axios.get(COURSE_API_ENDPOINT, createHeaders()) }
    getById(id) { return axios.get(COURSE_API_ENDPOINT + '/' + id, createHeaders()) }

    addCourse(course) { return axios.post(COURSE_API_ENDPOINT, course, createHeaders()) }

    updateCourse(id, course) { return axios.put(COURSE_API_ENDPOINT + '/' + id, course, createHeaders()) }

    deleteCourse(id) { return axios.delete(COURSE_API_ENDPOINT + '/' + id, createHeaders()) }

}
export default new CourseService()