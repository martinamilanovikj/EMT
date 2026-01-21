import axios from 'axios';

const BOOKS_API_URL = "http://localhost:9090/api/book";

const bookRepository = {
    findAll: () => axios.get(BOOKS_API_URL),
    add: (bookData) => axios.post(`${BOOKS_API_URL}/add`, bookData),
    edit: (id, bookData) => axios.put(`${BOOKS_API_URL}/edit/${id}`, bookData),
    delete: (id) => axios.delete(`${BOOKS_API_URL}/delete/${id}`)
};

export default bookRepository;