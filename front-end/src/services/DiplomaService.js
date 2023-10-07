import axios from "axios";

const DIPLOMA_API_BASE_URL = 'http://localhost:8080/api/user/service/diploma/{serviceId}';

class DiplomaService {
    getDiploma() { return axios.get(DIPLOMA_API_BASE_URL); }
}

export default new DiplomaService()