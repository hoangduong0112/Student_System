import axios from "axios";
import cookie from "react-cookies";
import {createHeaders} from "./header";

const ONLINE_SERVICE_API_ENDPOINT = 'http://localhost:8080/api/online-service';

class OnlineService {
    acceptRequest(id) { return axios.get(ONLINE_SERVICE_API_ENDPOINT +"/" + id + '/accept', createHeaders()) }

    deleteRequest(id) {return axios.delete(ONLINE_SERVICE_API_ENDPOINT+"/"+id, createHeaders())}

    cancelRequest(id) {return axios.get(ONLINE_SERVICE_API_ENDPOINT+"/"+id + "/cancel", createHeaders())}

    getAllRequest(){return axios.get(ONLINE_SERVICE_API_ENDPOINT, createHeaders())}

    getRequestById(id){return axios.get(ONLINE_SERVICE_API_ENDPOINT + "/" + id, createHeaders())}

}
export default new OnlineService()