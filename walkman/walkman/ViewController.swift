//
//  ViewController.swift
//  walkman
//
//  Created by Timothy C on 8/30/18.
//  Copyright © 2018 Timothy C. All rights reserved.
//

import UIKit
import AVFoundation

class ViewController: UIViewController {
    
    var player:AVAudioPlayer = AVAudioPlayer()

    @IBAction func play(_ sender: Any) {
        player.play()
    }
    
    @IBAction func pause(_ sender: Any) {
        player.pause()
    }
    
    @IBAction func replay(_ sender: Any) {
        player.currentTime = 0
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        
        do
        {
            let audioPath = Bundle.main.path(forResource: "song", ofType: "mp4")
             try player = AVAudioPlayer(contentsOf: NSURL(fileURLWithPath: audioPath!) as URL)
        }
        catch
        {
            //Error
        }
    }


}

