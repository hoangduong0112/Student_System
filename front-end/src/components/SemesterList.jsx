import React, {Component} from 'react';
import { Container, Table } from 'reactstrap';
import Header from '../app/Header';
import SemesterService from "../services/SemesterService";

class SemesterList extends Component {
    constructor(props) {
        super(props);
        this.state = {
            semesters: []
        }
    }

    componentDidMount() {
        SemesterService.getSemester().then(res => {
            this.setState({semesters: res.data});
        });
    }

    render() {
        return (
            <div>
                <Header />
                <Container fluid>
                    <h3 className ="App">Cấp bằng tốt nghiệp</h3>
                    <div className="row">
                        <Table className="mt-4 table table-striped table-bordered">
                            <thead  className="text-center align-middle"><tr>
                                <th width="20%">Email</th>
                                <th >Bản sao tiếng Việt</th>
                                <th>Bản sao tiếng Anh</th>
                                <th>Số điện thoại</th>
                                <th width="30%">Nội dung</th>
                            </tr></thead>
                            <tbody>
                            { this.state.semesters.map( semester => (
                                <tr key={semester.id}>
                                    <td>{semester.email}</td>
                                    <td>{semester.vietCopy}</td>
                                    <td>{semester.engCopy}</td>
                                    <td>{semester.phoneContact}</td>
                                    <td>{semester.content}</td>
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
export default SemesterList;