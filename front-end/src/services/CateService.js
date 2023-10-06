import axios from "axios";

const CATE_API_BASE_URL = 'http://localhost:8080/api/guest/service-cate';

class CateService {
    getCate() { return axios.get(CATE_API_BASE_URL); }
}

export default new CateService()