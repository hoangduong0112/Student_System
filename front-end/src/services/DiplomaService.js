import axios from "axios";
import config from "./config";

const DIPLOMA_API_BASE_URL = 'http://localhost:8080/api/diploma';

class DiplomaService {
    getDiploma(serviceId) { return axios.get(DIPLOMA_API_BASE_URL + '/' + serviceId, config); }
    addDiploma(diploma) { return axios.post(DIPLOMA_API_BASE_URL, diploma, config); }
    updateDiploma(diploma, id) { return axios.put(DIPLOMA_API_BASE_URL + '/' + id, diploma, config); }
}

export default new DiplomaService()