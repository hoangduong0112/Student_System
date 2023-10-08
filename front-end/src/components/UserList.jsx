import React, {Component} from 'react';
import { Container, Table } from 'reactstrap';
import AppNavbar from '../app/AppNavbar';
import UserService from "../services/UserService";

class UserList extends Component {
    constructor(props) {
        super(props);
        this.state = {
            users: []

        }
    }

    componentDidMount() {
        UserService.getUser().then(res => {
            this.setState({users: res.data});
        });
    }

    render() {
        return (
            <div>
                <AppNavbar />
                <Container fluid>
                    <h3 className ="App">Thông tin sinh viên</h3>
                    <div className="row">
                        <Table className="mt-4 table table-striped table-bordered">
                            <thead  className="text-center  align-middle"><tr>
                                <th width="25%">Họ và tên</th>
                                <th width="25%">Email</th>
                                <th width="25%">Khoa</th>
                                <th width="25%">Ngành</th>
                            </tr></thead>
                            <tbody>
                            { this.state.users.map( user => (
                                <tr key={user.id}>
                                    <td>{user.fullName}</td>
                                    <td>{user.email}</td>
                                    <td>{user.department_name}</td>
                                    <td>{user.major_name}</td>
                                </tr>
                            ))}
                            </tbody>
                        </Table>
                    </div>
                </Container>
            </div>
        );
    }
}
export default UserList;