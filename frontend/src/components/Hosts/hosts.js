import React, { useState } from 'react';

const HostList = ({ hosts }) => {
    const [currentPage, setCurrentPage] = useState(0);
    const hostsPerPage = 3;

    const pageCount = Math.ceil(hosts.length / hostsPerPage);

    const currentHosts = hosts.slice(currentPage * hostsPerPage, (currentPage + 1) * hostsPerPage);

    const handlePageChange = (newPage) => {
        setCurrentPage(newPage);
    };

    return (
        <div className="container mt-3">
            <h2>Hosts</h2>
            <table className="table table-hover table-striped">
                <thead>
                <tr>
                    <th>#</th>
                    <th>Name</th>
                </tr>
                </thead>
                <tbody>
                {currentHosts.map((host, index) => (
                    <tr key={index}>
                        <td>{currentPage * hostsPerPage + index + 1}</td>
                        <td>{host.name}</td>
                    </tr>
                ))}
                </tbody>
            </table>
            {pageCount > 1 && (
                <nav>
                    <ul className="pagination">
                        {Array.from({ length: pageCount }, (_, i) => (
                            <li key={i} className={`page-item ${i === currentPage ? 'active' : ''}`}>
                                <button className="page-link" onClick={() => handlePageChange(i)}>
                                    {i + 1}
                                </button>
                            </li>
                        ))}
                    </ul>
                </nav>
            )}
        </div>
    );
};

export default HostList;
