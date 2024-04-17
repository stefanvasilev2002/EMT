import React from 'react';

const CategoryList = ({ categories }) => {
    return (
        <div className="container mt-3">
            <h2>Categories</h2>
            <table className="table table-hover table-striped">
                <thead>
                <tr>
                    <th>#</th>
                    <th>Name</th>
                </tr>
                </thead>
                <tbody>
                {categories.map((category, index) => (
                    <tr key={index}>
                        <td>{index + 1}</td>
                        <td>{category}</td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
};

export default CategoryList;
