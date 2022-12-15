package com.nhnacademy.department.listener;

import com.nhnacademy.department.domain.ManagementRegisterRequest;
import com.nhnacademy.department.service.ManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.Objects;
import java.util.StringTokenizer;

@RequiredArgsConstructor
@Component
public class ReadFileListener implements ApplicationListener<ApplicationStartedEvent> {

    private final ManagementService managementService;

    private static final String OPTION_NAME = "init.file.name";

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {

        String fileName = System.getProperty(OPTION_NAME);

        if (Objects.isNull(fileName)) {
            return;
        }

        File file = new File(fileName);

        if (!file.isFile()) {
            throw new RuntimeException("파일이 존재하지 않습니다.");
        }

        try {
            readFile(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void readFile(File file) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        StringTokenizer st;
        String line;

        while ((line = br.readLine()) != null) {
            String[] token = line.split(" ");
            ManagementRegisterRequest managementRequest = new ManagementRegisterRequest().builder()
                    .employeeId(token[0])
                    .employeeName(token[1])
                    .departmentName(token[2])
                    .departmentId(token[3])
                    .build();

            managementService.saveTextFile(managementRequest);
        }

        br.close();

    }
}
