import React, {useEffect, useState} from 'react'
import TranscriptService from "../../services/TranscriptService";
import {useLocation, useNavigate, useParams} from "react-router-dom";
import SemesterService from "../../services/SemesterService";

function UpdateTranscript() {
    const { id } = useParams();
    const loc = useLocation();
    const nav = useNavigate();
    const [err, setErr] = useState('');

    const {language, contactPhone, fromSemester, toSemester, quantity, isSealed} = loc.state || {};
    const [transcriptId, setTranscriptId] = useState(0);
    const [semesters, setSemesters] = useState([]);
    const [languageInput, setLanguageInput] = useState(language || '');
    const [contactPhoneInput, setContactPhoneInput] = useState(contactPhone || '');
    const [fromSemesterInput, setFromSemesterInput] = useState(fromSemester || 0);
    const [toSemesterInput, setToSemesterInput] = useState(toSemester || 0);
    const [quantityInput, setQuantityInput] = useState(quantity || '');
    const [isSealedInput, setIsSealedInput] = useState(isSealed || false);

    useEffect(() => {
        TranscriptService.getTranscript(id).then(res => {
            let transcript = res.data;
            setTranscriptId(transcript.id);
            setLanguageInput(transcript.language);
            setContactPhoneInput(transcript.contactPhone);
            setFromSemesterInput(transcript.fromSemester.id);
            setToSemesterInput(transcript.toSemester.id);
            setQuantityInput(transcript.quantity);
            setIsSealedInput(transcript.isSealed);
        })

        const getSemesters = async () => {
            try {
                const res = await SemesterService.getAllSemester();
                setSemesters(res.data);
            } catch (error) {
                console.error('Lỗi khi lấy danh sách học kỳ:', error);
            }
        };

        getSemesters().then();
    }, [id]);

    const saveTranscript = (e) => {
        e.preventDefault();
        if (contactPhoneInput === undefined || fromSemesterInput === undefined || languageInput === undefined
            || toSemesterInput === undefined || quantityInput === undefined)
            setErr('Vui lòng nhập đầy đủ thông tin');
        else if (quantityInput <= 0)
            setErr('Số bản nhập không hợp lệ');
        else if (toSemesterInput < fromSemesterInput)
            setErr('Học kỳ chọn không hợp lệ');
        else {
            const transcript = {
                language: languageInput,
                fromSemester: fromSemesterInput,
                toSemester: toSemesterInput,
                quantity: quantityInput,
                contactPhone: contactPhoneInput,
                isSealed: isSealedInput,
            };

            TranscriptService.updateTranscript(transcript, transcriptId).then(() => {

            })
        }
    };

    const changeLanguageHandler = (e) => {
        setLanguageInput(e.target.value);
        setErr('');
    }

    const changePhoneHandler = (e) => {
        setContactPhoneInput(e.target.value);
        setErr('');
    }

    const changeFromSemesterHandler = (e) => {
        setFromSemesterInput(parseInt(e.target.value));
        setErr('');
    }

    const changeToSemesterHandler = (e) => {
        setToSemesterInput(parseInt(e.target.value));
        setErr('');
    }

    const changeQuantityHandler = (e) => {
        setQuantityInput(parseInt(e.target.value));
        setErr('');
    }

    const changeSealedHandler = (e) => {
        setIsSealedInput(e.target.value);
        setErr('');
    }

    const cancel = () => { nav(`/home`); }

    return (
        <div>
            <div className = "container">
                <div className = "row">
                    <div className = "card col-md-6 offset-md-3">
                        <h3 className="mt-2 text-center">Chỉnh sửa bảng điểm</h3>
                        <div className = "card-body">
                            <form>
                                <div className = "form-group">
                                    <label>Ngôn ngữ</label>
                                    <input placeholder="Tiếng Việt, Anh ..." name="language" className="form-control"
                                           value={languageInput} onChange={changeLanguageHandler}/>
                                </div>
                                <div className = "form-group">
                                    <label>Học kỳ bắt đầu</label>
                                    <select name="from semester" className="form-control"
                                            value={fromSemesterInput} onChange={changeFromSemesterHandler}>
                                        <option value="">Chọn học kỳ</option>
                                        {semesters.map((semester) => (
                                            <option key={semester.id} value={semester.id}>{semester.semesterName}</option>
                                        ))}
                                    </select>
                                </div>
                                <div className = "form-group">
                                    <label>Học kỳ kết thúc</label>
                                    <select name="to semester" className="form-control custom-select"
                                            value={toSemesterInput} onChange={changeToSemesterHandler}>
                                        <option value="">Chọn học kỳ</option>
                                        {semesters.map((semester) => (
                                            <option key={semester.id} value={semester.id}>{semester.semesterName}</option>
                                        ))}
                                    </select>
                                </div>
                                <div className = "form-group">
                                    <label>Số điện thoại</label>
                                    <input placeholder="Số điện thoại" name="phoneContact" className="form-control"
                                           value={contactPhoneInput} onChange={changePhoneHandler}/>
                                </div>
                                <div className = "form-group">
                                    <label>Số bản sao</label>
                                    <input placeholder="Số bản" name="quantity" type="number" min="1" className="form-control"
                                           value={quantityInput} onChange={changeQuantityHandler}/>
                                </div>
                                <div className="form-check form-check-inline">
                                    <input className="form-check-input" type="checkbox"
                                           checked={isSealedInput} onChange={changeSealedHandler}/>
                                    <label className="form-check-label">Niêm phong (trường hợp gửi qua nước ngoài)</label>
                                </div>
                                <div className="text-end mt-2">
                                    <button className="btn btn-primary me-1" onClick={saveTranscript}>Lưu</button>
                                    <button className="btn btn-secondary ms-1" onClick={cancel}>Hủy</button>
                                </div>
                            </form>
                        </div>
                        {err && <div onMouseEnter={() => setErr('')} className="alert alert-danger">{err}</div>}
                    </div>
                </div>
            </div>
        </div>
    )
}

export default UpdateTranscript;