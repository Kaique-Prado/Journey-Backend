package com.journey_back.service;


import com.journey_back.model.LinkModel;
import com.journey_back.repository.LinkRepository;
import com.journey_back.request.LinkRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LinksService {

    @Autowired
    private LinkRepository linkRepository;

    // Construtor
    public LinksService(LinkRepository repository) {
        this.linkRepository = repository;
    }


    // Listar Links
    public List<LinkModel> getLinks() {
        List<LinkModel> list = linkRepository.findAll();
        return list;
    }

    // Inserir Links
    public LinkModel registerLink(LinkModel linkModel) {
        LinkModel link = linkModel;
        linkRepository.save(linkModel);
        return link;
    }

    // Atualizar Links
    public LinkModel updateLink(UUID id, LinkRequest linkRequest) {
       var link = linkRepository.findById(id);

       if (link.isPresent()) {
           LinkModel linkBefore = link.get();
           linkBefore.setTitle(linkRequest.title());
           linkBefore.setUrl(linkRequest.url());
           LinkModel newLink = linkBefore;
           this.linkRepository.save(newLink);
           return newLink;
       } else {
           throw new RuntimeException("Link não cadastrado");
       }
    }

    // Deletar Links
    public boolean deleteLink(UUID id) {
        var link = this.linkRepository.findById(id);

        if (link.isPresent()) {
            linkRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

}
