import axios from '../custom-axios/axios';

const AccommodationService = {
    fetchAccommodations: () => {
        return axios.get("/accommodations");
    },
    fetchCategories: () => {
        return axios.get("/accommodations/categories");
    },
    fetchHosts: () => {
        return axios.get("/hosts");
    },
    deleteAccommodation: (id) => {
        return axios.delete(`/accommodations/delete/${id}`);
    },
    getAccommodation: (id) => {
        var accommodation = axios.get(`/accommodations/${id}`);
        console.log(accommodation)
        return accommodation;
    },

    addAccommodation: (name, category, hostId, availableNights) => {
        return axios.post("/accommodations/add", {
            "name": name,
            "category": category,
            "hostId": hostId,
            "availableNights": availableNights
        });
    },
    editAccommodation: (id, name, category, hostId, availableNights) => {
        return axios.post(`/accommodations/edit/${id}`, {
            "name": name,
            "category": category,
            "hostId": hostId,
            "availableNights": availableNights
        });
    },

    lowerAvailableNights: (id) => {
        return axios.post(`/accommodations/lowerAvailableNights/${id}`);
    },
}

export default AccommodationService;
