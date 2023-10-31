import axios from "axios";
import cookie from "react-cookies";
import {createHeaders} from "./header";

const STUD_CERTIFICATE_API_BASE_URL = 'http://localhost:8080/api/stud-certificate';

class StudCertificateService {
    getStudCertificate(serviceId) { return axios.get(STUD_CERTIFICATE_API_BASE_URL + '/' + serviceId, createHeaders()); }
    addStudCertificate(studCertificate) { return axios.post(STUD_CERTIFICATE_API_BASE_URL, studCertificate, createHeaders()); }
    updateStudCertificate(studCertificate, studCertificateId)
    { return axios.put(STUD_CERTIFICATE_API_BASE_URL + '/' + studCertificateId, studCertificate, createHeaders()); }
}

export default new StudCertificateService()