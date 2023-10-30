import axios from "axios";
import config from "./config";

const SERVICE_CATE_API_URL = 'http://localhost:8080/api/service-cate';

class CateService {
    updateCate(ServiceCate, id) { return axios.put(SERVICE_CATE_API_URL + '/' + id, ServiceCate, config) }

    changeCate(id) { return axios.get(SERVICE_CATE_API_URL + '/'+ id + '/change', config) }

    getAllServiceCate() { return axios.get(SERVICE_CATE_API_URL, config) }

    getById(id) { return axios.get(SERVICE_CATE_API_URL + "/" + id, config) }
}
export default new CateService()