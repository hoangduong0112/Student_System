import axios from "axios";
import cookie from "react-cookies";
import {createHeaders} from "./header";

const SERVICE_CATE_API_URL = 'http://localhost:8080/api/service-cate';

class CateService {
    updateCate(ServiceCate, id) { return axios.put(SERVICE_CATE_API_URL + '/' + id, ServiceCate, createHeaders()) }

    changeCate(id) { return axios.get(SERVICE_CATE_API_URL + '/'+ id + '/change', createHeaders()) }

    getAllServiceCate() { return axios.get(SERVICE_CATE_API_URL, createHeaders()) }

    getById(id) { return axios.get(SERVICE_CATE_API_URL + "/" + id, createHeaders()) }
}
export default new CateService()