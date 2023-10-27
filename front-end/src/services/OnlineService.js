import axios from "axios";
import config from "./config";

const ONLINE_SERVICE_API_ENDPOINT = 'http://localhost:8080/api/online-service';

class OnlineService {
    acceptRequest(id) { return axios.put(ONLINE_SERVICE_API_ENDPOINT +"/" + id + '/accept', config) }

}
export default new OnlineService()