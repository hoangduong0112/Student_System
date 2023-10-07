import React, {Component} from 'react';
import { Container, Table } from 'reactstrap';
import Header from '../app/Header';
import StudCertificateService from "../services/StudCertificateService";

class StudCertificateList extends Component {
    constructor(props) {
        super(props);
        this.state = {
            studCertificates: []
        }
    }

    componentDidMount() {
        StudCertificateService.getStudCertificate().then(res => {
            this.setState({studCertificates: res.data});
        });
    }

    render() {
        return (
            <div>
                <Header />
                <Container fluid>
                    <h3 className ="App">Chứng nhận sinh viên</h3>
                    <div className="row">
                        <Table className="mt-4 table table-striped table-bordered">
                            <thead  className="text-center align-middle"><tr>
                                <th width="20%">Email</th>
                                <th >Bản sao tiếng Việt</th>
                                <th>Bản sao tiếng Anh</th>
                                <th>Số điện thoại</th>
                                <th width="30%">Nội dung</th>                            </tr></thead>
                            <tbody>
                            { this.state.studCertificates.map( studCertificate => (
                                <tr key={studCertificate.id}>
                                    <td>{studCertificate.email}</td>
                                    <td>{studCertificate.vietCopy}</td>
                                    <td>{studCertificate.engCopy}</td>
                                    <td>{studCertificate.phoneContact}</td>
                                    <td>{studCertificate.content}</td>
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
export default StudCertificateList;