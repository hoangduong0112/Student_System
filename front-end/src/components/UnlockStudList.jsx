import React, {Component} from 'react';
import { Container, Table } from 'reactstrap';
import Header from '../app/Header';
import UnlockStudService from "../services/UnlockStudService";

class UnlockStudList extends Component {
    constructor(props) {
        super(props);
        this.state = {
            unlockStuds: []
        }
    }

    componentDidMount() {
        UnlockStudService.getUnlockStud().then(res => {
            this.setState({unlockStuds: res.data});
        });
    }

    render() {
        return (
            <div>
                <Header />
                <Container fluid>
                    <h3 className ="App">Mở khóa sinh viên</h3>
                    <div className="row">
                        <Table className="mt-2 table table-striped table-bordered">
                            <thead  className="text-center align-middle"><tr>
                                <th width="70%">Nội dung</th>
                                <th width="30%">Hình ảnh</th>
                            </tr></thead>
                            <tbody>
                            { this.state.unlockStuds.map( unlockStud => (
                                <tr key={unlockStud.id}>
                                    <td>{unlockStud.content}</td>
                                    <td>{unlockStud.image}</td>
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
export default UnlockStudList;