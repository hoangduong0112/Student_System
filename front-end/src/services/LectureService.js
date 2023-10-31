import axios from "axios";
import cookie from "react-cookies";
import {createHeaders} from "./header";

const LECTURE_API_ADMIN_URL = 'http://localhost:8080/api/lecture';
class LectureService{
    getAllLecture() { return axios.get(LECTURE_API_ADMIN_URL, createHeaders()); }
    getLecture(id) { return axios.get(LECTURE_API_ADMIN_URL + "/" + id, createHeaders()); }
    addLecture(lecture) { return axios.post(LECTURE_API_ADMIN_URL, lecture, createHeaders()); }
    updateLecture(id, lecture) { return axios.put(LECTURE_API_ADMIN_URL + '/' + id, lecture, createHeaders()); }
    deleteLecture(id) { return axios.delete(LECTURE_API_ADMIN_URL + '/' + id, createHeaders()); }
}
export default new LectureService()