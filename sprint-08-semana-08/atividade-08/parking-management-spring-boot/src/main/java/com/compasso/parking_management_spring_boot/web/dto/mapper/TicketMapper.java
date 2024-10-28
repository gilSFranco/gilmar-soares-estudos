package com.compasso.parking_management_spring_boot.web.dto.mapper;

import com.compasso.parking_management_spring_boot.entities.Ticket;
import com.compasso.parking_management_spring_boot.web.dto.ticket.ResponseTicketDTO;
import com.compasso.parking_management_spring_boot.web.dto.ticket.TicketDTO;

import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class TicketMapper {

    public static ResponseTicketDTO toDTO(Ticket ticket){
        return new ModelMapper().map(ticket, ResponseTicketDTO.class);
    }

    public static List<ResponseTicketDTO> toListDTO(List<Ticket> ticketList){
        return ticketList.stream().map(ticket -> toDTO(ticket)).collect(Collectors.toList());
    }

    public static Ticket toTicket(TicketDTO ticketDTO){
        return new ModelMapper().map(ticketDTO, Ticket.class);
    }
}
