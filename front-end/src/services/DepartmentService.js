import axios from "axios";
import cookie from "react-cookies";
import {createHeaders} from "./header";

const DEPARTMENT_API_URL = 'http://localhost:8080/api/department';

class DepartmentService{

    getDepartment() { return axios.get(DEPARTMENT_API_URL, createHeaders()); }
    getDepartmentById(id) { return axios.get(DEPARTMENT_API_URL + '/' + createHeaders()); }
}
export default new DepartmentService()