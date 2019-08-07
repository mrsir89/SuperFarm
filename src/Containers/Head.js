import React from 'react';

const Header =() => (
    <div className="Header">
        <h1><a href="/" onClick={function(){
            this.props.onChangePage();
        }.bind(this)}>QnA</a></h1>
    </div>
)

export default Header;