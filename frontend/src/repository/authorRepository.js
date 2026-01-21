import axios from 'axios';

const AUTHORS_API_URL = "http://localhost:9090/api/author";

const authorRepository = {
    findAll: () => axios.get(AUTHORS_API_URL),


    add: (author) => axios.post(`${AUTHORS_API_URL}/add`, author),


    edit: (id, author) => axios.put(`${AUTHORS_API_URL}/edit/${id}`, author),


    delete: (id) => axios.delete(`${AUTHORS_API_URL}/delete/${id}`)
};

export default authorRepository;