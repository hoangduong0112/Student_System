import React, {Component} from 'react';
import { Container, Table } from 'reactstrap';
import AppNavbar from '../app/AppNavbar';
import CateService from "../services/CateService";

class CateList extends Component {
    constructor(props) {
        super(props);
        this.state = {
            cates: []

        }
    }

    componentDidMount() {
        CateService.getCate().then(res => {
            this.setState({cates: res.data});
        });
    }

    render() {
        return (
            <div>
                <AppNavbar />
                <Container fluid>
                    <h3 className ="App">Đăng ký dịch vụ</h3>
                    <div className="row">
                        <Table className="mt-4 table table-striped table-bordered">
                            <thead className="text-center align-middle"><tr>
                                <th width="30%">Dịch vụ</th>
                                <th width="20%">Đơn giá</th>
                                <th>Trạng thái</th>
                                <th width="30%">Nội dung</th>
                            </tr></thead>
                            <tbody>
                            { this.state.cates.map( cate => (
                                <tr key={cate.id}>
                                    <td>{cate.serviceCateName}</td>
                                    <td>{cate.price}</td>
                                    <td>{cate.isAvailable}</td>
                                    <td>{cate.description}</td>
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
export default CateList;