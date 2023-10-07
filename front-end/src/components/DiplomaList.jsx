import React, {Component} from 'react';
import { Container, Table } from 'reactstrap';
import Header from '../app/Header';
import DiplomaService from "../services/DiplomaService";

class DiplomaList extends Component {
    constructor(props) {
        super(props);
        this.state = {
            diplomas: []
        }
    }

    componentDidMount() {
        DiplomaService.getDiploma().then(res => {
            this.setState({diplomas: res.data});
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
                                <th>Số lượng bản sao</th>
                                <th>Số điện thoại</th>
                                <th width="30%">Email</th>
                                <th>Năm tốt nghiệp</th>
                                <th width="30%">Mã bằng</th>
                                <th>Ngày lập</th>
                            </tr></thead>
                            <tbody>
                            { this.state.diplomas.map( diploma => (
                                <tr key={diploma.id}>
                                    <td>{diploma.copy}</td>
                                    <td>{diploma.phoneContact}</td>
                                    <td>{diploma.email}</td>
                                    <td>{diploma.diplomaYear}</td>
                                    <td>{diploma.diplomaCode}</td>
                                    <td>{diploma.onlineService.createdDate}</td>
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
export default DiplomaList;