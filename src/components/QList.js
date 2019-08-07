import React from "react"

function QList(props){
    return (
        <div className="QList">
            <p>게시글 번호 : {props.item.productCode}</p>
            <p>회원 아이디 : {props.item.productName}</p>
            <p>제목 : {props.item.lowerCode}</p>
            <p>내용 : {props.item.productPrice}</p>
            <p>게시 날짜 : </p>
        </div>
    )
}

export default QnAList