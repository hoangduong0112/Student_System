import axios from "axios";

const UNLOCK_STUD_API_BASE_URL = 'http://localhost:8080/api/user/service/unlock-stud/{serviceId}';

class UnlockStudService {
    getUnlockStud() { return axios.get(UNLOCK_STUD_API_BASE_URL); }
}

export default new UnlockStudService()