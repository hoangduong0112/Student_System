import axios from "axios";
import cookie from "react-cookies";
import {createHeaders} from "./header";

const UNLOCK_STUD_API_BASE_URL = 'http://localhost:8080/api/unlock-student';

class UnlockStudService {
    getUnlockStud(serviceId) { return axios.get(UNLOCK_STUD_API_BASE_URL +"/"+ serviceId, createHeaders()); }
    addUnlockStud(unlockStud) { return axios.post(UNLOCK_STUD_API_BASE_URL, unlockStud, createHeaders()); }
    updateUnlockStud(unlockStud, unlockStudId) {
        return axios.put(UNLOCK_STUD_API_BASE_URL + '/' + unlockStudId, unlockStud, createHeaders()); }
}

export default new UnlockStudService()