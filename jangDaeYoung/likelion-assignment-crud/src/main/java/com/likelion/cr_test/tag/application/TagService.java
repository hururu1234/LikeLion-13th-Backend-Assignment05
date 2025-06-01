package com.likelion.cr_test.tag.application;


import com.likelion.cr_test.common.error.ErrorCode;
import com.likelion.cr_test.common.exception.BusinessException;
import com.likelion.cr_test.member.api.dto.request.MemberUpdateRequestDto;
import com.likelion.cr_test.member.api.dto.response.MemberInfoResponseDto;
import com.likelion.cr_test.member.domain.Member;
import com.likelion.cr_test.tag.api.dto.request.TagSaveRequestDto;
import com.likelion.cr_test.tag.api.dto.request.TagUpdateRequestDto;
import com.likelion.cr_test.tag.api.dto.response.TagInfoResponseDto;
import com.likelion.cr_test.tag.api.dto.response.TagListResponseDto;
import com.likelion.cr_test.tag.domain.Tag;
import com.likelion.cr_test.tag.domain.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TagService {

    private final TagRepository tagRepository;

    //태그 저장
    @Transactional
    public void tagSave(TagSaveRequestDto tagSaveRequestDto){

        //태그가 기존에 있는지 확인하는 부분
        Optional<Tag> storedTag = tagRepository.findByName(tagSaveRequestDto.tagName());

        //태그가 저장소에 없으면 새로 저장
        if (storedTag.isEmpty()) {
            Tag tag = Tag.builder()
                    .name(tagSaveRequestDto.tagName())
                    .build();
            tagRepository.save(tag);
        }
    }

    //태그id로 태그조회
    public TagInfoResponseDto findTagById(Long tagId){
        Tag tag = tagRepository.findById(tagId)
                .orElseThrow(() -> new BusinessException(ErrorCode.TAG_NOT_FOUND_EXCEPTION, ErrorCode.TAG_NOT_FOUND_EXCEPTION.getMessage() + tagId));
        return TagInfoResponseDto.from(tag);
    }

    //태그 전체 조회
    public TagListResponseDto findTagAll(){

        List<Tag> tags = tagRepository.findAll();
        List<TagInfoResponseDto> tagInfoResponseDtoList = tags.stream()
                .map(TagInfoResponseDto::from)
                .toList();

        return TagListResponseDto.from(tagInfoResponseDtoList);
    }

    //태그 업데이트
    @Transactional
    public void tagUpdate(Long tagId, TagUpdateRequestDto tagUpdateRequestDto){

        Tag tag = tagRepository.findById(tagId)
                .orElseThrow(() -> new BusinessException(ErrorCode.TAG_NOT_FOUND_EXCEPTION, ErrorCode.TAG_NOT_FOUND_EXCEPTION.getMessage() + tagId));

        tag.update(tagUpdateRequestDto);
    }

    //태그 삭제
    @Transactional
    public void tagDelete(Long tagId){
        Tag tag = tagRepository.findById(tagId)
                .orElseThrow(() -> new BusinessException(
                        ErrorCode.TAG_NOT_FOUND_EXCEPTION,
                        ErrorCode.TAG_NOT_FOUND_EXCEPTION.getMessage()));

        tagRepository.delete(tag);
    }








}
