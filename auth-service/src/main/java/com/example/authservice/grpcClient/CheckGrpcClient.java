package com.example.authservice.grpcClient;

import Grpc.Check;
import com.example.authservice.dto.maindto.CheckDto;
import com.example.authservice.dto.maindto.UserDTO;

public interface CheckGrpcClient {
    CheckDto[] getCheckByEmail(UserDTO userDTO);
    CheckDto changeSumByIdAndEmail(CheckDto checkDto);
    String changeEnableByIdAndEmail(CheckDto checkDto);
    CheckDto createCheckByEmail(CheckDto checkDto);
}
