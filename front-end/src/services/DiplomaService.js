import axios from "axios";
import cookie from "react-cookies";
import {createHeaders} from "./header";

const DIPLOMA_API_BASE_URL = 'http://localhost:8080/api/diploma';

class DiplomaService {
    getDiploma(serviceId) { return axios.get(DIPLOMA_API_BASE_URL + '/' + serviceId, createHeaders()); }
    addDiploma(diploma) { return axios.post(DIPLOMA_API_BASE_URL, diploma, createHeaders()); }
    updateDiploma(diploma, id) { return axios.put(DIPLOMA_API_BASE_URL + '/' + id, diploma, createHeaders()); }
}

export default new DiplomaService()