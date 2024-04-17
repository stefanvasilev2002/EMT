import './App.css';
import React, {Component} from "react";
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Header from '../Header/header';
import AccommodationAdd from "../Accommodations/AccommodationAdd/accommodationAdd";
import AccommodationEdit from "../Accommodations/AccommodationEdit/accommodationEdit";
import Accommodations from "../Accommodations/AccommodationList/accommodations";
import AccommodationService from "../../repository/accommodationRepository";
import CategoryList from "../Categories/categories";
import AccommodationLowerAvailableNights
    from "../Accommodations/AccommodationLowerAvailableNights/accommodationLowerAvailableNights.js";

class App extends Component {

  constructor(props) {
    super(props);
    this.state = {
      accommodations: [],
      hosts: [],
      categories: [],
      selectedAccommodation: {}
    }
  }

  render() {
    return (
        <Router>
            <Header />
            <main>
                <div className="container">
                    <Routes>
                        <Route
                            path={"/accommodations/add"}
                            element={<AccommodationAdd
                                categories={this.state.categories}
                                hosts={this.state.hosts}
                                onAddAccomodation={this.addAccommodation}
                            />}
                        />
                        <Route
                            path={"/accommodations/edit/:id"}
                            element={<AccommodationEdit
                                categories={this.state.categories}
                                onEditAccommodation={this.editAccommodation}
                                hosts={this.state.hosts}
                                accommodation={this.state.selectedAccommodation}
                            />}
                        />
                        <Route
                            path={"/accommodations"}
                            element={<Accommodations
                                accommodations={this.state.accommodations}
                                onDelete={this.deleteAccommodation}
                                onEdit={this.getAccommodation}
                                onLowerAvailableNights={this.lowerAvailableNights}
                            />}
                        />
                        <Route
                            path={"/"}
                            element={<Accommodations
                                accommodations={this.state.accommodations}
                                onDelete={this.deleteAccommodation}
                                onEdit={this.getAccommodation}
                                onLowerAvailableNights={this.lowerAvailableNights}
                            />}
                        />
                        <Route
                            path={"/categories"}
                            element={<CategoryList
                                categories={this.state.categories}
                            />}
                        />
                        {/* <Navigate to={"/accommodations"} /> */}
                    </Routes>
                </div>
            </main>
        </Router>
    );
  }

  componentDidMount() {
    this.fetchData()
  }

  fetchData = () => {
    this.loadCategories();
    this.loadAccommodations();
    this.loadHosts();
  }

  loadAccommodations = () => {
    AccommodationService.fetchAccommodations()
        .then((data) => {
          this.setState({
            accommodations: data.data
          })
        });
  }

  loadCategories = () => {
      AccommodationService.fetchCategories()
        .then((data) => {
          this.setState({
            categories: data.data
          })
        });
  }
  loadHosts = () => {
      AccommodationService.fetchHosts()
          .then((data) => {
              this.setState({
                  hosts: data.data
              })
          });
  }

  deleteAccommodation = (id) => {
      AccommodationService.deleteAccommodation(id)
        .then(() => {
          this.loadAccommodations();
        });
  }

  lowerAvailableNights = (id) => {
      AccommodationService.lowerAvailableNights(id)
          .then(() => {
              this.loadAccommodations();
          });
  }

  addAccommodation = (name, category, hostId, availableNights) => {
    AccommodationService.addAccommodation(name, category, hostId, availableNights)
        .then(() => {
          this.loadAccommodations();
        });
  }

  getAccommodation = (id) => {
      AccommodationService.getAccommodation(id)
        .then((data) => {
          this.setState({
            selectedAccommodation: data.data
          })
        })
      console.log(this.state)
  }

  editAccommodation = (id, name, category, hostId, availableNights) => {
    AccommodationService.editAccommodation(id, name, category, hostId, availableNights)
        .then(() => {
          this.loadAccommodations();
        });
  }

}

export default App;
