package com.example.authservice.grpcClient;

import Grpc.Check;
import com.example.authservice.dto.maindto.CheckDto;

public interface CheckGrpcClient {
    CheckDto[] getCheckByEmail(CheckDto checkDto);
    CheckDto changeSumByIdAndEmail(CheckDto checkDto);
    String changeEnableByIdAndEmail(CheckDto checkDto);
    CheckDto createCheckByEmail(CheckDto checkDto);
}
