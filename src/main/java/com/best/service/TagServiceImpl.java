package com.best.service;

import com.best.NotFoundException;
import com.best.dao.TagRepository;
import com.best.po.Tag;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagServiceImpl implements   TagService {

    @Autowired
    private TagRepository tagRepository;
    @Transactional
    @Override
    public Tag saveTag(Tag tag) {
        return tagRepository.save(tag);
    }
    @Transactional
    @Override
    public Tag getTag(Long id) {
        return tagRepository.getById(id);
    }
    @Transactional
    @Override
    public Page<Tag> listTag(Pageable pageable) {
        return tagRepository.findAll(pageable);
    }
    @Transactional
    @Override
    public Tag updateTag(Long id, Tag tag) {
        Tag t = tagRepository.getById(id);
        if (t==null){
            throw new NotFoundException("不存在该类型");
        }
        BeanUtils.copyProperties(tag,t);
        return tagRepository.save(t);
    }
    @Transactional
    @Override
    public void deleteTag(Long id) {
    tagRepository.deleteById(id);
    }
    @Transactional
    @Override
    public Tag getTagByName(String name) {
        return tagRepository.getByName(name);
    }

    @Override
    public List<Tag> listTag() {
        return tagRepository.findAll();
    }

    @Override
    public List<Tag> listTag(String ids) { //1,2,3
        return tagRepository.findAllById(convertToList(ids));
    }

    private  List<Long> convertToList(String ids){
        List<Long> list=new ArrayList<>();
        if(!"".equals(ids) && ids!=null){
            String[] idarray=ids.split(",");
            for(int i=0; i<idarray.length ;i++){
                list.add(new Long(idarray[i]));
            }
        }
        return list;
    }
}
