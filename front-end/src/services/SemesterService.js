import axios from "axios";
import config from "./config";

const SEMESTER_API_ADMIN_URL = 'http://localhost:8080/api/semester';

class SemesterService {
    getAllSemester() { return axios.get(SEMESTER_API_ADMIN_URL, config); }
    addSemester(semester) { return axios.post(SEMESTER_API_ADMIN_URL, semester, config); }

    getSemesterById(id) { return axios.get(SEMESTER_API_ADMIN_URL + '/' + id, config); }

    updateSemester(id, semester) { return axios.put(SEMESTER_API_ADMIN_URL + '/' + id, semester, config); }

    deleteSemester(id) { return axios.delete(SEMESTER_API_ADMIN_URL + '/' + id, config); }

    getAvailableSemester() { return axios.get(SEMESTER_API_ADMIN_URL + '/available', config); }
    setFinish(id) { return axios.get(SEMESTER_API_ADMIN_URL + "/"+ id +'/setFinish', config); }

    addSemesterUser(request) { return axios.put(SEMESTER_API_ADMIN_URL + '/semester-user', request, config); }
    addSemesterDetail(request) { return axios.post(SEMESTER_API_ADMIN_URL + '/semester-detail', request, config); }
}

export default new SemesterService()