import axios from "axios";

const TRANSCRIPT_API_BASE_URL = 'http://localhost:8080/api/user/service/transcript/{serviceId}';

class TranscriptService {
    getTranscript() { return axios.get(TRANSCRIPT_API_BASE_URL); }
}

export default new TranscriptService()