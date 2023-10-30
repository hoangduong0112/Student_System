import config from "./config";
import axios from "axios";

const DEPARTMENT_API_URL = 'http://localhost:8080/api/department';

class DepartmentService{

    getDepartment() { return axios.get(DEPARTMENT_API_URL); }
    getDepartmentById(id) { return axios.get(DEPARTMENT_API_URL + '/' + id); }
}
export default new DepartmentService()