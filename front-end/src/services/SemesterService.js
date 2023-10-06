import axios from "axios";

const SEMESTER_API_BASE_URL = 'http://localhost:8080/api/user/semester/{serviceId}';

class SemesterService {
    getSemester() { return axios.get(SEMESTER_API_BASE_URL); }
}

export default new SemesterService()