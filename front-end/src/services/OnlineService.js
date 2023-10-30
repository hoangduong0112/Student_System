import axios from "axios";
import config from "./config";

const ONLINE_SERVICE_API_ENDPOINT = 'http://localhost:8080/api/online-service';

class OnlineService {
    acceptRequest(id) { return axios.get(ONLINE_SERVICE_API_ENDPOINT +"/" + id + '/accept', config) }

    deleteRequest(id) {return axios.delete(ONLINE_SERVICE_API_ENDPOINT+"/"+id, config)}

    cancelRequest(id) {return axios.get(ONLINE_SERVICE_API_ENDPOINT+"/"+id,config)}

    getAllRequest(){return axios.get(ONLINE_SERVICE_API_ENDPOINT, config)}

    getRequestById(id){return axios.get(ONLINE_SERVICE_API_ENDPOINT + "/" + id, config)}

}
export default new OnlineService()