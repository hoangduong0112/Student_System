import axios from "axios";
import config from "./config";

const LECTURE_API_ADMIN_URL = 'http://localhost:8080/api/lecture';
class LectureService{
    getAllLecture() { return axios.get(LECTURE_API_ADMIN_URL, config); }
    getLecture(id) { return axios.get(LECTURE_API_ADMIN_URL + "/" + id, config); }
    addLecture(lecture) { return axios.post(LECTURE_API_ADMIN_URL, lecture, config); }
    updateLecture(id, lecture) { return axios.put(LECTURE_API_ADMIN_URL + '/' + id, lecture, config); }
    deleteLecture(id) { return axios.delete(LECTURE_API_ADMIN_URL + '/' + id, config); }
}
export default new LectureService()