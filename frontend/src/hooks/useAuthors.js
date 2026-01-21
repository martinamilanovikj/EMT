import { useCallback, useEffect, useState } from "react";
import authorRepository from "../repository/authorRepository";

const initialState = {
    authors: [],
    loading: true,
};

const useAuthors = () => {
    const [state, setState] = useState(initialState);

    const fetchAuthors = useCallback(() => {
        authorRepository.findAll()
            .then((response) => {
                setState({ authors: response.data, loading: false });
            })
            .catch(console.log);
    }, []);

    const onAdd = useCallback((data) => {
        authorRepository.add(data).then(fetchAuthors).catch(console.log);
    }, [fetchAuthors]);

    const onEdit = useCallback((id, data) => {
        authorRepository.edit(id, data).then(fetchAuthors).catch(console.log);
    }, [fetchAuthors]);

    const onDelete = useCallback((id) => {
        authorRepository.delete(id).then(fetchAuthors).catch(console.log);
    }, [fetchAuthors]);

    useEffect(() => {
        fetchAuthors();
    }, [fetchAuthors]);

    return { ...state, onAdd, onEdit, onDelete };
};

export default useAuthors;