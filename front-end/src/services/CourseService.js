import axios from "axios";
import config from "./config";

const COURSE_API_ENDPOINT = 'http://localhost:8080/api/course';

class CourseService {
    getAll() { return axios.get(COURSE_API_ENDPOINT, config) }
    getById(id) { return axios.get(COURSE_API_ENDPOINT + '/' + id, config) }

    addCourse(course) { return axios.post(COURSE_API_ENDPOINT, course, config) }

    updateCourse(id, course) { return axios.put(COURSE_API_ENDPOINT + '/' + id, course, config) }

    deleteCourse(id) { return axios.delete(COURSE_API_ENDPOINT + '/' + id, config) }

}
export default new CourseService()