package com.itsure.admin.service.impl;


import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;


import com.itsure.admin.mapper.ArticleMapper;
import com.itsure.admin.entity.Article;
import com.itsure.admin.service.IArticleService;


@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService {


}