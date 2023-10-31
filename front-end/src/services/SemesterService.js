import axios from "axios";
import cookie from "react-cookies";
import {createHeaders} from "./header";

const SEMESTER_API_ADMIN_URL = 'http://localhost:8080/api/semester';

class SemesterService {
    getAllSemester() { return axios.get(SEMESTER_API_ADMIN_URL, createHeaders()); }
    addSemester(semester) { return axios.post(SEMESTER_API_ADMIN_URL, semester, createHeaders()); }

    getSemesterById(id) { return axios.get(SEMESTER_API_ADMIN_URL + '/' + id, createHeaders()); }

    updateSemester(id, semester) { return axios.put(SEMESTER_API_ADMIN_URL + '/' + id, semester, createHeaders()); }

    deleteSemester(id) { return axios.delete(SEMESTER_API_ADMIN_URL + '/' + id, createHeaders()); }

    getAvailableSemester() { return axios.get(SEMESTER_API_ADMIN_URL + '/available', createHeaders()); }
    setFinish(id) { return axios.get(SEMESTER_API_ADMIN_URL + "/"+ id +'/setFinish', createHeaders()); }

    addSemesterUser(request) { return axios.put(SEMESTER_API_ADMIN_URL + '/semester-user', request, createHeaders()); }
    addSemesterDetail(request) { return axios.post(SEMESTER_API_ADMIN_URL + '/semester-detail', request, createHeaders()); }
}

export default new SemesterService()