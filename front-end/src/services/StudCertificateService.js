import axios from "axios";

const STUD_CERTIFICATE_API_BASE_URL = 'http://localhost:8080/api/user/service/stud-certificate/{serviceId}';

class StudCertificateService {
    getStudCertificate() { return axios.get(STUD_CERTIFICATE_API_BASE_URL); }
}

export default new StudCertificateService()