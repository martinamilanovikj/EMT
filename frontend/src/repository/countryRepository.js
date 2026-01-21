import axios from 'axios';

const COUNTRIES_API_URL = "http://localhost:9090/api/country";

const countryRepository = {
    findAll: () => axios.get(COUNTRIES_API_URL),


    add: (countryData) => axios.post(`${COUNTRIES_API_URL}/add`, countryData),


    edit: (id, countryData) => axios.put(`${COUNTRIES_API_URL}/edit/${id}`, countryData),


    delete: (id) => axios.delete(`${COUNTRIES_API_URL}/delete/${id}`)
};

export default countryRepository;
