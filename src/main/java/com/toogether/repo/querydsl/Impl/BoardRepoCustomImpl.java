package com.toogether.repo.querydsl.Impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.toogether.repo.querydsl.BoardRepoCustom;
import com.toogether.vo.QBoardVO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BoardRepoCustomImpl implements BoardRepoCustom {
    private final JPAQueryFactory queryFactory;
    private final QBoardVO board = QBoardVO.boardVO;
    @Override
    public int findByMax(){
        int result = queryFactory.select(board.boardID.max())
                .from(board)
                .fetchOne();
        return result;
    }


}
