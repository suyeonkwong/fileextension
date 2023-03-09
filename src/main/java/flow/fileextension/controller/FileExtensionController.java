package flow.fileextension.controller;

import flow.fileextension.dto.fileextension.FileExtensionRequest;
import flow.fileextension.dto.fileextension.FileExtensionResponse;
import flow.fileextension.service.FileExtensionService;
import flow.fileextension.util.constant.FileExtensionType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequiredArgsConstructor
@RequestMapping("/fileextension")
public class FileExtensionController {

    private final FileExtensionService fileExtensionService;

    @GetMapping
    public String getFileExtensions(Model model) {
        model.addAttribute("fixedFileExtensions", fileExtensionService.getFileExtensions(FileExtensionType.FIX));
        model.addAttribute("customFileExtensions", fileExtensionService.getFileExtensions(FileExtensionType.CUSTOM));
        return "fileExtension";
    }

    @PatchMapping
    @ResponseBody
    public void updateFixedFileExtensionType(@RequestParam("fileExtensionSeq") Long fileExtensionSeq) {
        fileExtensionService.updateFileExtensionType(fileExtensionSeq);
    }

    @PostMapping
    @ResponseBody
    public FileExtensionResponse addCustomFileExtension(@RequestBody FileExtensionRequest fileExtensionRequest) {
        return fileExtensionService.addCustomFileExtensionCheckSize(fileExtensionRequest.getFileExtensionName());
    }

}
